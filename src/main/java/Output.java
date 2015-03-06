import java.util.List;

/**
 * Created by germanbarros on 3/6/15.
 */
public class Output {

  private String formatLib;
  private String beanioStream;
  private String method;
  private List<String> imports;

  public static Builder newBuilder() {
    return new Builder();
  }

  public List<String> getImports() {
    return imports;
  }

  public void setImports(List<String> imports) {
    this.imports = imports;
  }

  private Output(Builder builder) {
    setFormatLib(builder.formatLib);
    setBeanioStream(builder.beanioStream);
    setMethod(builder.method);
    setImports(builder.imports);
  }

  public String getFormatLib() {
    return formatLib;
  }

  public void setFormatLib(String formatLib) {
    this.formatLib = formatLib;
  }

  public String getBeanioStream() {
    return beanioStream;
  }

  public void setBeanioStream(String beanioStream) {
    this.beanioStream = beanioStream;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }


  public static final class Builder {

    private String method;
    private List<String> imports;
    private String beanioStream;
    private String formatLib;

    public Builder() {
    }

    public Builder withMethod(String method) {
      this.method = method;
      return this;
    }

    public Builder withImports(List<String> imports) {
      this.imports = imports;
      return this;
    }

    public Builder withBeanioStream(String beanioStream) {
      this.beanioStream = beanioStream;
      return this;
    }

    public Builder withFormatLib(String formatLib) {
      this.formatLib = formatLib;
      return this;
    }

    public Output build() {
      return new Output(this);
    }
  }



}
