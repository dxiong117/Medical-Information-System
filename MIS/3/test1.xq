import module namespace solve = "http://www.example.org/xquery/clinic/solution" at "/D:/2021spring/cs548/e3/10402740/GetProviderInfo.xquery";
import schema namespace  c = "http://cs548.stevens.edu/clinic/db" at "/D:/2021spring/cs548/e3/Assignment3-XQuery.xsd";


let $db := doc ("instance1.xml")/c:Assignment3-XQuery
return solve:getProviderInfo($db)