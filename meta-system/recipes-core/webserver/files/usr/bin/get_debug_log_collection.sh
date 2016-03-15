#!/bin/sh

test -e /etc/rc.config && . /etc/rc.config

DT=$(date +%Y%m%d_%H%M)
LOG_ARCH=$1
LOG_DIR="/tmp/mld_log_$$"
export DISPLAY=:0

rm -rf "$LOG_DIR" > /dev/null 2>&1
mkdir -p "$LOG_DIR"
cd "$LOG_DIR"

# Sammle Hardwareinformationen ein
dmesg > dmesg.out 2>&1
biosinfo > bios.out 2>&1
lsmod > lsmod.out 2>&1
lsusb -v > lsusb.out 2>&1
lspci -vn > lspci.out 2>&1
ps ww > ps.out 2>&1
biosinfo > qmb.out 2>&1
uname -a > uname.out 2>&1
set > set.out 2>&1
xrandr > xrandr.out 2>&1
vdpauinfo > vdpauinfo.out 2>&1

# Sammle Umgebungsinformationen ein
ls -l /tmp/ > lltmp.out 2>&1
ls -l /usr/local/src/ > llsrc.out 2>&1
cat /proc/meminfo > meminfo.out 2>&1
if [ -e /usr/bin/vcgencmd ]; then
    vcgencmd measure_clock arm | /usr/bin/tr -d "frequency(45)=" / 1000000 >arm_cpuinfo.out 2>&1
else
    cat /proc/cpuinfo > cpuinfo.out 2>&1
fi
cat /proc/bus/input/devices > input.out 2>&1
top -b -d 1 -n 5 > top.out 2>&1
df > df.out
mount > mount.out 2>&1
if [ -e /usr/bin/vcgencmd ]; then
    CPU=$(vcgencmd measure_temp | /usr/bin/tr -d "temp=")
    MB=
elif [ -n "$(/var/lib/dpkg/info/sensor.list)" ]; then
    CPU=$(sensors | grep -i 'temp1\|cpu temp\|cpu0 temp\|core0 temp' | head -n1 | sed 's/.* //')
    MB=$( sensors | grep -i 'temp2\|m/b temp\|system temp' | head -n1 | sed 's/.* //')
    echo -ne "Temperatures:\tCPU: "$CPU"\tMB: "$MB > measure.out 2>&1
else
    echo "Achtung: kein Addon 'sensors' installiert" > measure.out 2>&1
fi

# Sammle Netzwerkinformationen ein
ifconfig > net.out 2>&1
ping -c 2 -w 5 www.minidvblinux.de >> net.out 2>&1
echo "" >> net.out 2>&1
echo "resolv.conf:" >> net.out 2>&1
cat /etc/resolv.conf >> net.out 2>&1
echo "Hostname: $(hostname)" >> net.out 2>&1
echo "DNS-Domain: $(dnsdomainname)" >> net.out 2>&1

# Sammle MLD-Informationen und Installationsinformation ein
apt list --installed 2>/dev/null | sort > dpkg_installed.out
apt list --upgradable 2>/dev/null | sort > dpkg_upgradable.out
cat /var/lib/dpkg/arch >> arch.out 2>&1

FILES="/etc/vdr/setup.conf /etc/vdr/channels.conf /etc/vdr/remote.conf \
/etc/X11/xorg.conf /root/.xine/config /root/.xine/config_xineliboutput \
/etc/asound.conf /etc/asound.state /etc/lircd.conf /etc/fstab /etc/rc.config \
/var/lib/dpkg/status"
cp -a $FILES . 2>/dev/null

# Sammle die Logfiles aus dem Verzeichnis /var/log/
cp -ar /var/log $LOG_DIR
catsysinit > $LOG_DIR/log/sysinit

# Sammle die Informationen über die Audio Möglichkeiten

aplay -lL > aplay.out 
for i in /proc/asound/card[0-9] ; do
   cnum=${i#*card}
   echo "SoundCard $cnum :" >> sound.out
   amixer contents  -c $cnum >> sound.out
   amixer scontents -c $cnum >> sound.out
   alsactl store $cnum -f /etc/asound.state
   echo "" >> sound.out
done
alsa-info.sh --with-aplay --with-amixer --with-alsactl --with-configs --with-devices --with-dmesg --output alsa-info.txt --no-upload >/dev/null 2>&1

tar -czhf "$LOG_ARCH" *
rm -rf "$LOG_DIR"

svdrpsend.sh MESG "$LOG_ARCH wurde erstellt"

if [ "$OUTPUT" = "-m" ] ; then
   TARGET=$(mount | grep " /media/" | tail -n 1 | cut -f 3 -d " ")
   if [ "$TARGET" != "" ] && [ -d "$TARGET" ] ; then
      OUTPUT="${TARGET}/"
      cp -f "$LOG_ARCH" "$TARGET/"
      sleep 3
      svdrpsend.sh MESG "$LOG_ARCH wurde nach $TARGET kopiert"
   fi
elif [ "$OUTPUT" != "" ] ; then
   cp "$LOG_ARCH" "$OUTPUT.tar.gz"
   svdrpsend.sh MESG "$LOG_ARCH wurde nach $OUTPUT.tar.gz kopiert"
fi
