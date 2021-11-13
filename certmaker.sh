#!/bin/bash
host_name="www.themoviedb.org"
$echo | openssl s_client -servername $host_name -connect $host_name:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > certificate.crt