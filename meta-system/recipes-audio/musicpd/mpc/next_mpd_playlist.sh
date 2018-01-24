#!/bin/sh
# Copyright 2013 Stephen L Arnold (stephen.arnold42 _AT_ gmail.com)
# Distributed under the terms of the GNU General Public License v2
#
# /usr/bin/next_mpd_playlist.sh
# A next-playlist wrapper for mpc that maintains a volatole per-session state
# and resets to the first playlist on reboot (ignores playlist named all).
# The playlist state is persistent on systems with a non-volatile /tmp dir.
#   non-persistent path: /tmp
#   persistent path: /var/lib/mpd
# Also resets the volume to the standard 0db value (86 on the alsamixer scale).
# Comment out the amixer call below to disable volume reset or remove the
# sed call to stop the filtering of all.
#
# Run as root or add your user to the mpd and audio groups.

PATH=/bin:/sbin:/usr/bin:/usr/sbin

PLAYLISTS=$(mpc lsplaylists | sort | xargs echo | sed "s/all //")

if [ -z "$PLAYLISTS" ] ; then
    echo "No playlists defined!"
    exit 1
fi

function do_next() {
    CURRENT_INDEX=/var/lib/mpd/current_playlist_index
    SIZE="$#"
    let "MAX = SIZE - 1"

    if [ -f "${CURRENT_INDEX}" ] ; then
        CURRENT=$(cat "${CURRENT_INDEX}")
    else
        CURRENT="$MAX"
        echo ${CURRENT} > "${CURRENT_INDEX}"
    fi

    declare -a pls=("$@")
    let "NEXT = CURRENT + 1"
    if [ $NEXT -gt $MAX ] ; then
        NEXT="0"
    fi

    echo "$MAX $CURRENT $NEXT ${pls[${NEXT}]}"
    mpc stop
    mpc clear
    amixer -q sset PCM 0
    mpc load "${pls[${NEXT}]}"
    mpc play
}

do_next ${PLAYLISTS}

if [ $? -eq 0 ]; then
    echo ${NEXT} > "${CURRENT_INDEX}"
fi
