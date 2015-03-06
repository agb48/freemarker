package ${generator.packageName};


<#list generator.imports as imp>
import ${imp};
</#list>


public class ${generator.className} extends Generator {

    private NTransactionData source;
    private ${generator.destinationClassName} destination;

<#if generator.output.formatLib = "BEANIO" >
    private StreamFactory factory;
</#if>

    public ${generator.className}() {
<#if generator.output.formatLib = "BEANIO">
        factory = StreamFactory.newInstance();
        factory.loadResource("mapping.xml");
</#if>
    }



    private void assign() {
        try{
        <#list generator.generics as generic>
            <#if generic.condition??>
            if ( ${generic.condition} ) {
                destination.${generic.destination} = ${generic.expression};
            }
            <#else>
            destination.${generic.destination} = ${generic.expression};
            </#if>
        </#list>
        }catch(Exception e){
            throw Throwables.propagate(e);
        }
    }


    <#if generator.methods??>
        private void callCustomAssignMethod(){
            try{
                <#list generator.methods as customMethodAssign>
                destination.${customMethodAssign.name}(
                    <#if customMethodAssign.params??>
                        <#list customMethodAssign.params as param>
                            ${param.name}
                        </#list>
                    </#if>
                );
                </#list>
            }catch(Exception e){
                throw Throwables.propagate(e);
            }
        }

    </#if>



    private String format() {
        try {
            <#if generator.output.formatLib = "BEANIO">
                try(StringWriter stringWriter = new StringWriter()){
                    factory.loadResource("mapping.xml");
                    BeanWriter beanWriter = factory.createWriter("${generator.output.beanioStream}",
                    stringWriter);
                    beanWriter.write(destination);
                    stringWriter.flush();
                    beanWriter.close();
                    return stringWriter.getBuffer().toString();
                }
            </#if>

            <#if generator.output.formatLib = "METHOD">
                destination.${generator.output.method};
            </#if>
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
    }

    public String build(Source source, Destination destination) {
        this.source = (NTransactionData) source;
        this.destination = (${generator.destinationClassName}) destination;
        assign();
        <#if generator.methods??>
        callCustomAssignMethod();
        </#if>
        return format();
    }
}
