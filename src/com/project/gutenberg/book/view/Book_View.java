package com.project.gutenberg.book.view;

import com.project.gutenberg.book.pagination.Line_Measurer;

public abstract class Book_View {
    protected Book_Formatting formatting;
    protected Line_Measurer line_measurer;
    protected String[][] prev_current_next_page_lines;
    protected Page_View prev_page;
    protected Page_View current_page;
    protected Page_View next_page;
    protected final int prev_page_stack_id = -1;
    protected final int current_page_stack_id = 0;
    protected final int next_page_stack_id = 1;

    protected Book_View(int width, int height, int font_size) {
        formatting = new Book_Formatting(width, height, font_size);
        prev_current_next_page_lines = new String[3][formatting.get_lines_per_page()];
    }
    public String[] get_page_lines(int page_index) {
        return prev_current_next_page_lines[page_index];
    }
    public Book_Formatting get_formatting() {
        return formatting;
    }
    public Line_Measurer get_line_measurer() {
        return line_measurer;
    }
    public void set_prev_current_next_page_lines(String[][] prev_current_next_page_lines) {
        this.prev_current_next_page_lines = prev_current_next_page_lines;
    }

}