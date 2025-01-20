package com.tuusuario.pizzetos;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PlaceDetailsResponse {

    @SerializedName("result")
    private Result result;

    public Result getResult() {
        return result;
    }

    public static class Result {
        @SerializedName("reviews")
        private List<Review> reviews;

        public List<Review> getReviews() {
            return reviews;
        }
    }

    public static class Review {
        @SerializedName("author_name")
        private String authorName;

        @SerializedName("rating")
        private int rating;

        @SerializedName("text")
        private String text;

        @SerializedName("relative_time_description")
        private String relativeTimeDescription;

        public String getAuthorName() {
            return authorName;
        }

        public int getRating() {
            return rating;
        }

        public String getText() {
            return text;
        }

        public String getRelativeTimeDescription() {
            return relativeTimeDescription;
        }
    }
}
