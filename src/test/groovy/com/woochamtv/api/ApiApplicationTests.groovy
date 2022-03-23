package com.woochamtv.api

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class ApiApplicationTests extends Specification {

    def "contextLoads" () {
        expect:
        true
    }

}