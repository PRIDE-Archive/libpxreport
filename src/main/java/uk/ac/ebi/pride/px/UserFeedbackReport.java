package uk.ac.ebi.pride.px;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 10:36
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public interface UserFeedbackReport extends Report {
    // Rating constants
    int RATING_VERY_BAD = 0;
    int RATING_BAD = 1;
    int RATING_NEUTRAL = 2;
    int RATING_GOOD = 3;
    int RATING_VERY_GOOD = 4;

    // Methods
    void setRating(int rating);
    void setComments(String comments);
    int getRating();
    String getComments();

}
