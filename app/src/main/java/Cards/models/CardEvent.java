package Cards.models;
/**
 * Last Updated: 11/23/2020
 * Card Event Data Model
 *
 * @AUTHOR Devin M. O'Brien
 * TODO Remake Card Event based on Translator Class
 */

import java.time.LocalDateTime;

public class CardEvent {

	private String title;
	private String description;
	private Object startDate;
	private Object endDate;
	private boolean allDay;

	int getDate() {
		// Just So it compiles
		return 0;
	}
}
