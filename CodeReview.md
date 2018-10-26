# Code Review

Common Steps Performed:
---------------------------------
1. Since in the current project, I am using testng and not Junit, we should remove Junit dependency from pom
2. Updated the chrome driver file since old file was corrupted

SignInTest:
---------------------------------
1. Added setup and tearDown method
2. Added chrome options to disable the browser location notification
3. SignIn button in the modal was present in a frame, code to switch on the frame was missing, added that

HotelBookingTest:
---------------------------------
1. Added setup and tearDown method
2. The webElements was not initialized, they were throwing null pointer exception so initalizing the webElements using page Factory

FlightBookingTest:
---------------------------------
1. Added setup and tearDown method in FlightBookingTest Class
2. Increased the wait timeout to 5000ms for From web element because the auto complete list was not getting loaded and we were using first element of list hence, Index Out Of Bound Exception with the time 2000ms
3. Corrected the id defined for To Web element
4. Increased the wait timeout to 5000ms for To web element because the auto complete list was not getting loaded and we were using first element of list hence, Index Out Of Bound Exception with the time 2000ms
5. In order to face any past date issue I am always selecting the last row and last clickable column from the date picker

Optimizations:
---------------------------------
1. There was redundant code present in all the three test classes so I have created a Base Class, moved all the common methods to the base class and used inheritance to extend the base class in all the three test classes

Suggestions:
---------------------------------
1. As we have used Thread.sleep() method for wait, we could have used Explicit/Implicit waits to avoid Thread.sleep. Explicit/Implicit waits don't wait for entire time mentioned to wait, if the element is found early, next steps will be executed.
2. Instead of running test individually we can use testng.xml and run it through pom.xml