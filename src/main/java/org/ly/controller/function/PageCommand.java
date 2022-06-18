package org.ly.controller.function;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;

public interface PageCommand {

    PageRequest pageRequestGenerate();

    Criteria queryGenerate();
}
