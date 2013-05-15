package com.mvc.jump;

import java.util.List;

public interface JumpContainer {

    void init(Config config);

    List<Object> loadAllBeans();
}
