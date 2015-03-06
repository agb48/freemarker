/**
 * Created by germanbarros on 3/6/15.
 */
public class MethodParam {

  private String name;

  private MethodParam(Builder builder) {
    setName(builder.name);
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


  public static final class Builder {

    private String name;

    private Builder() {
    }

    public Builder withName(final String name) {
      this.name = name;
      return this;
    }

    public MethodParam build() {
      return new MethodParam(this);
    }
  }
}
