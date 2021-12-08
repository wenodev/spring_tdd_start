//package com.spring_tdd_start.filter;
//
//public class BookFilter {
//    private String name;
//    private String author;
//
//    public String getName() {
//        return name;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public BookFilter(String name, String author) {
//        this.name = name;
//        this.author = author;
//    }
//
//    public static class Builder{
//        private String name;
//        private String author;
//
//        public Builder name(String name){
//            this.name = name;
//            return this;
//        }
//
//        public Builder author(String author){
//            this.name = author;
//            return this;
//        }
//
//        public BookFilter build(){
//            return new BookFilter(name, author);
//        }
//    }
//
//    public static Builder builder(){
//        return new Builder();
//    }
//
//    public static BookFilter byName(String name) {
//        return BookFilter.builder().name(name).build();
//    }
//
//    public static BookFilter byAuthor(String author) {
//        return BookFilter.builder().author(author).build();
//    }
//
//    public BookFilter and(BookFilter filter) {
//        return filter;
//    }
//}
