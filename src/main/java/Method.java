import java.util.List;

/**
 * Created by germanbarros on 3/6/15.
 */
public class Method {

  private String name;
  private List<MethodParam> params;

  private Method(Builder builder) {
    setName(builder.name);
    setParams(builder.params);
  }

  public static Builder newBuilder() {
    return new Builder();
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<MethodParam> getParams() {
    return params;
  }

  public void setParams(List<MethodParam> params) {
    this.params = params;
  }


  public static final class Builder {

    private String name;
    private List<MethodParam> params;

    private Builder() {
    }

    public Builder withName(String name) {
      this.name = name;
      return this;
    }

    public Builder withParams(List<MethodParam> params) {
      this.params = params;
      return this;
    }

    public Method build() {
      return new Method(this);
    }
  }
}
