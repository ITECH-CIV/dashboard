package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.model.Page;

public interface PageService extends GenericService<Page, Long> {
    long getTotalViews();
    Page getByLabel(String label);

}
