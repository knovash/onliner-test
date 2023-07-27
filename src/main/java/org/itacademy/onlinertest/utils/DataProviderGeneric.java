package org.itacademy.onlinertest.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class DataProviderGeneric {

    @DataProvider
    public Object[][] getData(Method method) throws FileNotFoundException {
        log.info("DATAPROVIDER GENERIC START");
        log.info("METHOD: " + method.getName());
        log.info("PATH: " + method.getAnnotation(TestData.class).path());
        log.info("FILE: " + method.getAnnotation(TestData.class).filename());
        log.info("MODEL TYPE: " + method.getAnnotation(TestData.class).modelType());
        String path = method.getAnnotation(TestData.class).path();
        String fileName = method.getAnnotation(TestData.class).path();
        String methodName = method.getName();
        URL resourceItems;
        File jsonFile;
        if (fileName == "") {
            resourceItems = JsonGenericUtils.class.getClassLoader().getResource(path + methodName + ".json");
        } else {
            fileName = method.getAnnotation(TestData.class).filename();
            resourceItems = JsonGenericUtils.class.getClassLoader().getResource(path + fileName);
        }
        jsonFile = new File(Objects.requireNonNull(resourceItems).getFile());
        log.info("JSON FILE: " + jsonFile);
        String jsonData = new JsonParser().parse(new FileReader(jsonFile)).toString();
        log.info("JSON DATA: " + jsonData);

        ArrayList<Object> list = null;
        try {
            list = new Gson().fromJson(jsonData, TypeToken.getParameterized(List.class,
                    Class.forName("org.itacademy.onlinertest.models." +
                    method.getAnnotation(TestData.class).modelType())).getType());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Object[][] result = Objects.requireNonNull(list).stream()
                .map(data -> new Object[]{data})
                .toArray(Object[][]::new);
        log.info("DATAPROVIDER END OK: " + result.length);
        return result;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface TestData {

        String path() default "";

        String modelType();

        String filename() default "";
    }
}
