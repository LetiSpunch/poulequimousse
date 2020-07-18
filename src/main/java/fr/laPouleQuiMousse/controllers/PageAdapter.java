package fr.laPouleQuiMousse.controllers;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PageAdapter<T> {


    private static final int DEFAULT_MAX_PAGES = 10;

    private int maxPages;

    private Page<T> page;

    private List<PageNumber> pageNumbers = new ArrayList<>();

    public PageAdapter(Page<T> page) {
        this(page, DEFAULT_MAX_PAGES);
    }

    public PageAdapter(Page<T> page, int maxPages) {
        this.page = page;
        this.maxPages = maxPages;
        preparePages();
    }

    private void preparePages() {
        int currentNumber = getNumber();
        int start, size;
        if (page.getTotalPages() <= maxPages) {
            start = 1;
            size = page.getTotalPages();
        } else {
            if (currentNumber <= maxPages - maxPages / 2) {
                start = 1;
            } else if (currentNumber >= page.getTotalPages() - maxPages / 2) {
                start = page.getTotalPages() - maxPages + 1;
            } else {
                start = currentNumber - maxPages / 2;
            }
            size = maxPages;
        }

        for (int i = 0; i < size; i++) {
            pageNumbers.add(new PageNumber(start + i, (start + i) == currentNumber));
        }
    }


    public boolean displayFirstPage() {
        return getNumber() > getMaxPages() / 2 + 1;
    }


    public boolean displayLastPage() {
        return getNumber() < getTotalPages() - getMaxPages() / 2;
    }

    public List<PageNumber> getPageNumbers() {
        return pageNumbers;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public List<T> getContent() {
        return page.getContent();
    }

    public int getNumber() {
        int number = page.getNumber();
        return number + 1;
    }

    public int getPage() {
        return getNumber();
    }

    public int getTotalPages() {
        return page.getTotalPages();
    }

    public int getSize() {
        return page.getSize();
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isSorted() {
        return page.getSort().isSorted();
    }

    public SortAdapter getSort() {
        return new SortAdapter(page.getSort());
    }

    public class PageNumber {
        private int number;
        private boolean current;

        public PageNumber(int number, boolean current) {
            this.number = number;
            this.current = current;
        }

        public int getNumber() {
            return number;
        }

        public boolean isCurrent() {
            return current;
        }

        @Override
        public String toString() {
            return String.valueOf(number);
        }
    }
}

