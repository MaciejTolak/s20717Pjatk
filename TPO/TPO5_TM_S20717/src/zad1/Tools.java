/**
 *
 *  @author Tolak Maciej S20717
 *
 */

package zad1;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class Tools {
    public static Options createOptionsFromYaml(String filename) throws FileNotFoundException {

        Yaml yaml = new Yaml();

        Map<String, Object> map=  yaml.load(new FileInputStream(filename));

        Map<String, List<String>> mapClient = (Map<String, List<String>>) map.get("clientsMap");

        return new Options((String)map.get("host"),(int)map.get("port"),(boolean)map.get("concurMode"),(boolean)map.get("showSendRes"),mapClient);
    }
}
