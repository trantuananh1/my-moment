"User":{
<#list value as item>
    "${item.id?j_string}":{
        "href":"/user/${item.id?j_string}",
        "version":"${item.version?c}",
        "data":{
            "username":"${item.username?j_string}",
            "lastIP":"<#if item.lastIp??>${item.lastIp?j_string}</#if>",
            "enabled":"<#if item.enabled??>${item.enabled?c}<#else></#if>"
        }
    }<#sep>,</#sep>
</#list>
}