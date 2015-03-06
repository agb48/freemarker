/**
 * Created by germanbarros on 3/6/15.
 */
public class Generic {

  private String condition;
  //could be filed, method name or java destination
  private String expression;
  // Destination does not goes into annotation, it's the field where we are applying the annotation
  private String destination;

  public Generic(String condition, String expression, String destination) {
    this.condition = condition;
    this.expression = expression;
    this.destination = destination;
  }

  private Generic(Builder builder) {
    setCondition(builder.condition);
    setExpression(builder.expression);
    setDestination(builder.destination);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }


  public static final class Builder {

    private String condition;
    private String expression;
    private String destination;

    private Builder() {
    }

    public Builder withCondition(String condition) {
      this.condition = condition;
      return this;
    }

    public Builder withExpression(String expression) {
      this.expression = expression;
      return this;
    }

    public Builder withDestination(String destination) {
      this.destination = destination;
      return this;
    }

    public Generic build() {
      return new Generic(this);
    }
  }
}
