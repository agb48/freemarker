import com.google.common.collect.ImmutableList;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by germanbarros on 3/6/15.
 */
public class Main {

  public static void main(String[] args) throws Exception {
    //Freemarker configuration object
    Configuration configuration = new Configuration();
    try {

      FileTemplateLoader templateLoader =
          new FileTemplateLoader(
              new File("/Users/germanbarros/IdeaProjects/sss/src/main/resources"));
      configuration.setTemplateLoader(templateLoader);

      Template template = configuration.getTemplate("class.ftl");

      // Build the data-model
      Map<String, Object> data = new HashMap<>();

      Generator.Builder generatorBuilder = fillMainClassData();
      appendAssignedFields(generatorBuilder);
      appendCustomMethods(generatorBuilder);

      appendOutput(generatorBuilder);

      data.put("generator", generatorBuilder.build());

      // Console output
      Writer out = new OutputStreamWriter(System.out);
      template.process(data, out);
      out.flush();


    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();
    }
  }

  private static void appendOutput(Generator.Builder generatorBuilder) {

    ImmutableList.Builder importsBuilder = ImmutableList.builder();

    importsBuilder.add("org.beanio.BeanWriter");
    importsBuilder.add("org.beanio.StreamFactory");
    importsBuilder.add("java.io.StringWriter");

    Output output = new Output.Builder()
        .withFormatLib("BEANIO")
        .withBeanioStream("meterSysToXml")
        .withImports(importsBuilder.build())
        .build();

    generatorBuilder.withOutput(output);
  }

  private static void appendCustomMethods(Generator.Builder generatorBuilder) {
    ImmutableList.Builder<Method> methods = ImmutableList.builder();

    methods.add(Method.newBuilder()
                    .withName("printIt")
                    .withParams(ImmutableList.<MethodParam>of(MethodParam.newBuilder()
                                                                  .withName("source")
                                                                  .build())).build());

    methods.add((Method.newBuilder())
                    .withName("print2")
                    .withParams(ImmutableList.<MethodParam>of(MethodParam.newBuilder()
                                                                  .withName("source")
                                                                  .build())).build());

    methods.add((Method.newBuilder())
                    .withName("methodNoParams")
                    .build());

    generatorBuilder.withMethods(methods.build());
  }

  private static void appendAssignedFields(Generator.Builder generatorBuilder) {
    ImmutableList.Builder<Generic> fields = ImmutableList.builder();

    fields.add(Generic.newBuilder()
                   .withCondition("source.track1 == \"2\"")
                   .withExpression("source.name")
                   .withDestination("des").build());

    fields.add(Generic.newBuilder()
                   .withCondition("source.track1 == destination.callme()")
                   .withExpression("destination.doSomething(source.track1)")
                   .withDestination("destinationVariableName").build());

    fields.add(Generic.newBuilder()
                   .withExpression("destination.callMe()")
                   .withDestination("destinationVariableName").build());

    fields.add(Generic.newBuilder()
                   .withCondition("destination.callSomeInternalMethod(source)")
                   .withExpression("destination.callMe()")
                   .withDestination("destinationVariableName").build());

    fields.add(Generic.newBuilder()
                   .withExpression("source.track1.subString(1)")
                   .withDestination("destinationVariableName").build());

    generatorBuilder.withGenerics(fields.build());

  }

  private static Generator.Builder fillMainClassData() {
    ImmutableList.Builder<String> importBuilder = ImmutableList.builder();
    importBuilder.add("com.google.common.base.Throwables");

    Generator.Builder generatorBuilder = Generator.newBuilder();
    generatorBuilder.withClassName("ElavonGenerator");
    generatorBuilder.withImports(importBuilder.build());
    generatorBuilder.withPackageName("com.verifone.pimplugin.generator");
    generatorBuilder.withDestinationClassName("ElavonDto");

    return generatorBuilder;
  }

}
