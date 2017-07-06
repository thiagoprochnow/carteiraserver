# Stack

This files relates to AWS CloudFormation Template.  The initial stack necessary for this project includes
a static website (S3 + cloudfront) accessible through HTTPS

## Commands

`aws --no-verify-ssl --profile admin cloudformation create-stack --stack-name proch-api --template-body file://static-website.yaml --parameters ParameterKey=HostedZoneId,ParameterValue=ZKZITTXZD20D8 ParameterKey=DomainName,ParameterValue=proch-api.benito.com.br ParameterKey=RedirectDomainName,ParameterValue='' ParameterKey=CertificateType,ParameterValue=AcmCertificateArn  ParameterKey=ExistingCertificate,ParameterValue='arn:aws:acm:us-east-1:027131729208:certificate/48732881-c7d0-4540-8931-99119d2da340'`

Modify parameters accordingly:

* `--no-verify-ssl` is only necessary if you are behind a HTTPS proxy that interferes with direct connection to AWS services
* `--profile <AWS cli profile with rights to create stack>`
* `--stack-name <name>` give stack a name
* `HostedZoneId=<route53 of the domain hosted>` ID of the domain zone hosted on Route53
* `DomainName=<domain or subdomain name for the website>` a domain name for the website (will be created)
* `RedirectDomainName=<optional redirect domain name for the website>` another name for the website that will redirect to domain name above (will be created)
* `CertificateType=AcmCertificateArn` this option tell stack to use a existing ACM certificate, there is another option for create.
* `ExistingCertificate=<arn of ACM certificate>` as instructed by above option, use the certificated pointed by this parameter

