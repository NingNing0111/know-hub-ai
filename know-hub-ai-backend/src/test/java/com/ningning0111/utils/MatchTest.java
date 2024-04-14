package com.ningning0111.utils;

/**
 * @author ：何汉叁
 * @date ：2024/4/13 19:40
 * @description：TODO
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTest {

    @Test
    public void getMinioFileName_returnsFileName_whenUrlHasNoQueryParameters() {
        MatchUtil matchUtil = new MatchUtil();
        String url = "http://example.com/path/to/file.jpg";
        String expected = "file.jpg";
        String actual = matchUtil.getMinioFileName(url);
        assertEquals(expected, actual);
    }

    @Test
    public void getMinioFileName_returnsFileName_whenUrlHasQueryParameters() {
        MatchUtil matchUtil = new MatchUtil();
        String url = "http://example.com/path/to/1111file.jpg?param=value";
        String expected = "1111file.jpg";
        String actual = matchUtil.getMinioFileName(url);
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void getMinioFileName_returnsEmptyString_whenUrlHasNoFileName() {
        MatchUtil matchUtil = new MatchUtil();
        String url = "http://example.com/path/to/";
        String expected = "";
        String actual = matchUtil.getMinioFileName(url);
        assertEquals(expected, actual);
    }

    @Test
    public void getMinioFileName_returnsEmptyString_whenUrlIsEmpty() {
        MatchUtil matchUtil = new MatchUtil();
        String url = "";
        String expected = "";
        String actual = matchUtil.getMinioFileName(url);
        assertEquals(expected, actual);
    }
}