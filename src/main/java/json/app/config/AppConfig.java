package json.app.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.app.Util.FileIOImpl;
import json.app.Util.ReaderImpl;
import json.app.Util.ValidatorUtilImpl;
import json.app.Util.contract.FileIO;
import json.app.Util.contract.Reader;
import json.app.Util.contract.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public FileIO fileReader(){
        return new FileIOImpl();
    }
    @Bean
    public Reader reader(){
        return new ReaderImpl();
    }
    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
