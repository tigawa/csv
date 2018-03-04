#!/bin/bash

jconsole $(ps aux | grep CsvWriter | grep -v grep | awk '{ print $2 }')
