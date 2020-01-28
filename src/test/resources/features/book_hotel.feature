Feature: User can manage hotel bookings

  Scenario: User can add a new booking
    Given user adds a new booking
    Then the booking is displayed in the bookings list

  Scenario: User can delete a booking
    Given user adds a new booking
    And the booking is displayed in the bookings list
    When the user deletes the booking
    Then the booking is not displayed in the bookings list
