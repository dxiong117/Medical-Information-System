import schema namespace k = "http://cs548.stevens.edu/clinic/db" at "/D:/2021spring/cs548/e3/Assignment3-XQuery.xsd";
declare namespace pr = "http://www.example.org/clinic/providers";
declare namespace functx = "http://www.functx.com";

declare function functx:distinct-deep 
  ( $nodes as node()* )  as node()* {

    for $seq in (1 to count($nodes))
    return $nodes[$seq][not(functx:is-node-in-sequence-deep-equal(
                          .,$nodes[position() < $seq]))]
 } ;
 
declare function functx:is-node-in-sequence-deep-equal 
  ( $node as node()? ,
    $seq as node()* )  as xs:boolean {

   some $nodeInSeq in $seq satisfies deep-equal($nodeInSeq,$node)
 } ;

declare function local:getProviderInfo($db as element(k:clinic)) {
<pr:clinic>
{ 
for $p in functx:distinct-deep($db/k:patient/k:treatment/k:provider)
return 
    <pr:provider>
        <pr:name> {$p/k:name/text()} </pr:name>
        <pr:npi> {$p/k:npi/text()} </pr:npi>
        <pr:patients> 
        {for $pt in $db/k:patient
            where $pt/k:treatment/k:provider/k:name = $p/k:name 
                  return                  
            <pr:patient>            
            <pr:patient-id> { $pt/k:patient-id/text() } </pr:patient-id>
             <pr:patient-name> { $pt/k:name/text() } </pr:patient-name>
             <pr:treatments> 
                {for $t in $pt/k:treatment                    
                    where $t/k:provider/k:npi = $p/k:npi
                        return
                    <pr:treatment>
                        <pr:id> {$t/k:id/text()} </pr:id>
                        <pr:diagnosis> {$t/k:diagnosis/text()} </pr:diagnosis>                
                        <pr:drug> {$t/k:drug/text()} </pr:drug>
                    </pr:treatment>
                    }
             </pr:treatments>
        </pr:patient>          
          
        } 
        </pr:patients>
    </pr:provider>
}
    </pr:clinic>
 };
    
local:getProviderInfo(doc("instance1.xml")/k:clinic)
