package org.itechciv.dashboard.iservice;

import org.itechciv.dashboard.dto.PageViewDto;
import org.itechciv.dashboard.model.PageView;

public interface PageViewService extends GenericService<PageView, Long> {

    PageView savePageView(PageViewDto pageViewDto);
}
