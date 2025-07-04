# EC2 Public IP Access Policy Guide

Overview
Creating an IAM policy to allow EC2 instances to be publicly accessible for your Django website.

Available Options

Option 1 - Using AmazonEC2FullAccess
1. Go to IAM in AWS Console
2. Navigate to "Policies"
3. Search for "AmazonEC2FullAccess"
4. Attach this policy to your IAM user/role

Option 2 - Custom Policy (More secure, recommended)
1. Go to IAM in AWS Console
2. Choose "Create Policy"
3. Use this JSON policy:


{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ec2:AuthorizeSecurityGroupIngress",
                "ec2:RevokeSecurityGroupIngress",
                "ec2:UpdateSecurityGroupRuleDescriptionsIngress",
                "ec2:DescribeSecurityGroups",
                "ec2:DescribeSecurityGroupRules",
                "ec2:DescribeSecurityGroupReferences"
            ],
            "Resource": "*"
        }
    ]
}
This custom policy allows:
* Managing inbound security group rules
* Viewing security group configurations
* Necessary permissions for managing public access

AWS CLI Commands Implementation

1. Create the Custom Policy


# Create the policy
aws iam create-policy \
    --policy-name DjangoEC2SecurityPolicy \
    --policy-document '{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "ec2:AuthorizeSecurityGroupIngress",
                "ec2:RevokeSecurityGroupIngress",
                "ec2:UpdateSecurityGroupRuleDescriptionsIngress",
                "ec2:DescribeSecurityGroups",
                "ec2:DescribeSecurityGroupRules",
                "ec2:DescribeSecurityGroupReferences"
            ],
            "Resource": "*"
        }
    ]
}'

2. Attach Policy to IAM User
```bash
# Replace YOUR_USER_NAME with your IAM username
aws iam attach-user-policy \
    --user-name YOUR_USER_NAME \
    --policy-arn arn:aws:iam::YOUR_ACCOUNT_ID:policy/DjangoEC2SecurityPolicy
```

3. Verify Policy Attachment
```bash
# List attached policies for your user
aws iam list-attached-user-policies --user-name YOUR_USER_NAME
```

Notes:
- Replace `YOUR_USER_NAME` with your actual IAM username
- Replace `YOUR_ACCOUNT_ID` with your AWS account ID
- You can get your AWS account ID using: `aws sts get-caller-identity --query Account --output text`

Alternative: Using AmazonEC2FullAccess
If you prefer using the full EC2 access policy instead:
```bash
aws iam attach-user-policy \
    --user-name YOUR_USER_NAME \
    --policy-arn arn:aws:iam::aws:policy/AmazonEC2FullAccess
```\n\nLast updated: Fri Jul  4 13:06:11 CDT 2025
