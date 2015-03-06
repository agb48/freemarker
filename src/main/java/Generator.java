import java.util.List;

/**
 * Created by germanbarros on 3/6/15.
 */
public class Generator {

  private List<String> imports;
  private String destinationClassName;
  private String packageName;
  private String className;
  private List<Generic> generics;
  private List<Method> methods;
  private Output output;

  public static Builder newBuilder() {
    return new Builder();
  }

  public Output getOutput() {
    return output;
  }

  public void setOutput(Output output) {
    this.output = output;
  }

  public List<Method> getMethods() {
    return methods;
  }

  public void setMethods(List<Method> methods) {
    this.methods = methods;
  }

  public List<Generic> getGenerics() {
    return generics;
  }

  public void setGenerics(List<Generic> generics) {
    this.generics = generics;
  }

  public String getDestinationClassName() {
    return destinationClassName;
  }

  public void setDestinationClassName(String destinationClassName) {
    this.destinationClassName = destinationClassName;
  }

  private Generator(Builder builder) {
    setImports(builder.imports);
    destinationClassName = builder.destinationClassName;
    setPackageName(builder.packageName);
    setClassName(builder.className);
    setGenerics(builder.generics);
    setMethods(builder.methods);
    setOutput(builder.output);
  }

  public Generator(List<String> imports, String packageName, String className) {
    this.imports = imports;
    this.packageName = packageName;
    this.className = className;
  }

  public List<String> getImports() {
    return imports;
  }

  public void setImports(List<String> imports) {
    this.imports = imports;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }


  public static final class Builder {

    private Output output;
    private List<Method> methods;
    private List<Generic> generics;
    private String className;
    private String packageName;
    private List<String> imports;
    private String destinationClassName;

    public Builder() {
    }

    public Builder withOutput(Output output) {
      this.output = output;
      return this;
    }

    public Builder withMethods(List<Method> methods) {
      this.methods = methods;
      return this;
    }

    public Builder withGenerics(List<Generic> generics) {
      this.generics = generics;
      return this;
    }

    public Builder withClassName(String className) {
      this.className = className;
      return this;
    }

    public Builder withPackageName(String packageName) {
      this.packageName = packageName;
      return this;
    }

    public Builder withImports(List<String> imports) {
      this.imports = imports;
      return this;
    }

    public Builder withDestinationClassName(final String destinationClassName) {
      this.destinationClassName = destinationClassName;
      return this;
    }

    public Generator build() {
      return new Generator(this);
    }
  }



}
