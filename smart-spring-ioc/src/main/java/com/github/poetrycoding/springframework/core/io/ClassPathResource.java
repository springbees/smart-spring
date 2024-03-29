package com.github.poetrycoding.springframework.core.io;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ClassUtil;
import com.github.poetrycoding.springframework.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Description: 从Class类路径加载资源
 * <br/>
 * ClassPathResource
 *
 * @author laiql
 * @date 2023/4/27 20:53
 */
public class ClassPathResource implements Resource {
    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if (inputStream == null) {
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return inputStream;
    }
}
