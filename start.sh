#!/bin/bash
script_dir="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
working_dir="$(pwd)"
if [ "$working_dir" != "$script_dir" ]; then
	    cd "$script_dir"
fi
if [ -f "./build/libs/L2JStatsService.jar" ]; then
	    echo "L2JStatsService.jar exists"
	        java -jar "./build/libs/L2JStatsService.jar"
	else
		    echo "L2JStatsService.jar does not exist"
fi
