package bdd.demo.appjava.api.pub.home;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class HomeResponse {
    private String name;
    private String shortname;
    private String version;
}
