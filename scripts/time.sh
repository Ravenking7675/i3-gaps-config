#!/bin/bash
ar=0
func() {
	a=$RANDOM
	l=$(( ${#a}+1 ))
	rand=$(( a%l ))
	rand=$(( $rand))
	ar=$rand
}
read -p "Enter number of seconds to wait? : " seconds
s1=$(date +%s)
arr=("\033[32m| |\033[0m" "\033[35m#\033[0m" "\033[33m#\033[0m" "\033[34m#\033[0m")
while (( s <= seconds))
do
	s2=$(date +%s)
	func
	echo -e -n "${arr[ar]}"
	if (( s2-s1 == $seconds ))
	then
		echo
		echo
		echo "your $seconds seconds are over"
		clear
		exit 0
	fi
done