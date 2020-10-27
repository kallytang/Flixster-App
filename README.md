# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#) and view trailers with [YouTube Android Player API](https://developers.google.com/youtube/android/player/#). User is able to tap on movies listed to view movie trailer as well as the rating and overview of the movie. 

App allows user to view the list of movies in landscape mode as well as click on the list of movies to see movie details. Used 
Learned how to create heterogenous recyclerviews and shared transitions. Created an alternative Landscape view mode. 



### App Walkthough GIF

#### Implementation of shared transition  <br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_transition.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/transitions_land.gif" width=450><br>

##### Implementation of trailers for popular movies to play automatically, and less popular vids have an image placeholder
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_different_rating_views.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/different_rating_views_land.gif" width=450><br>

##### Implementation of play icon AND rounded images<br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_playicon.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_playicon_land.gif" width=450><br>

GIF created with [LiceCap](http://www.cockos.com/licecap/).

### Notes
NOTE: all movies have ratings higher than 5, instead of movies greater than 5 looking different, it'll be 7.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

### Technology Used

- [The Movie Database API](http://docs.themoviedb.apiary.io/#).
- [YouTube Android Player API](https://developers.google.com/youtube/android/player/#).
