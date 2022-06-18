package itmo.tech.main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestInfo {
    private Object[] args;
    private String methodName;

    public RequestInfo(String methodName, Object... args) {
        this.args = args;
        this.methodName = methodName;
    }
}
