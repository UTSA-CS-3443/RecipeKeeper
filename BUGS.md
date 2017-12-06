Bugs found in process but cannot be resolved due to short of time

1. main view -> list view (check out one recipe) -> read mode -> edit mode(1) (backward) -> read mode (backward)
-> list view (forward) -> read mode (forward) -> edit mode(2)
  - Expected: edit mode of recipe from (1). Result: edit mode of a blank recipe
  - Cause: shallow copy of data between scenes
  - Resolution: create an abstract Controller class. Addresses will then actually 
  contains two stacks of controllers, not just directories

2. In edit mode, if user switches to another scene when modified the recipe without saving the recipe,
there is no message pops up and warn the user about loss of data.
  - Cause: incompletion of compareTo method in Recipe class
  - Resolution: finish the method mentioned above
