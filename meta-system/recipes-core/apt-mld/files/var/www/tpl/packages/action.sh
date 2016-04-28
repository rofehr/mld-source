<? 
. functions.sh

session_start
include tpl/login.sh
login || return

export QUIET=1

head -c 1000 < /dev/zero | /usr/bin/tr '\0' ' '; echo

case "$GET_action" in
	install)
		echo "Install $GET_package"
		lock apt-get -y install $GET_package
		check_status
		;;
	remove)
		echo "Uninstall $GET_package"
		lock apt-get -y remove $GET_package && lock apt-get -y autoremove
		check_status
		;;
	upgrade)
		echo "Upgrade ${GET_package:-all packages}"
		if [ "$GET_package" ]; then
			lock apt-get -y --only-upgrade install $GET_package
		else
			lock apt-get -y dist-upgrade
		fi
		check_status
		;;
	update)
		echo "Update package list"
		lock apt-get update
		check_status
		;;
	restartVDR)
		restart vdr
		;;
esac 2>&1 | weblog
?>