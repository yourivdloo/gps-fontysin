az login

$subscription = "Fontys DienstIT PaaS Productieomgeving";
$result= az account show;
$name = $result[6] -replace '  "name": ', "";
$name = $name -replace ',', "";

if($name -NotMatch $subscription){
    az account set --subscription $subscription
}

mvn clean package
mvn azure-webapp::deploy