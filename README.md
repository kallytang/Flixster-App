# Flix
Flix is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.

#### BONUS
- [x] Implement a shared element transition when user clicks into the details of a movie (1 point)
- [x] Trailers for popular movies are played automatically when the movie is selected (1 point).
  - [x] When clicking on a popular movie (i.e. a movie voted for more than 7 stars) the video should be played immediately.
  - [x] Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- [x] Add a play icon overlay to popular movies to indicate that the movie can be played (1 point).
- [ ] Apply the popular ButterKnife annotation library to reduce view boilerplate. (1 point)
- [x] Add a rounded corners for the images using the Glide transformations. (1 point)

### App Walkthough GIF

implementation of shared transition  <br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_transition.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/transitions_land.gif" width=450><br>

implementation of trailers for popular movies to play automatically, and less popular vids have an image placeholder
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_different_rating_views.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/different_rating_views_land.gif" width=450><br>

implementation of play icon AND rounded images<br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_playicon.gif" width=250><br>
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_playicon_land.gif" width=450><br>

### Notes
NOTE: all movies have ratings higher than 5, instead of movies greater than 5 looking different, it'll be 7.

## Open-source libraries used
- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android
---

## Flix Part 1

### User Stories

#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [x] (2pts) Views should be responsive for both landscape/portrait mode.
   - [x] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [x] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [x] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [x] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF
Portrait Mode:
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_portrait.gif" width=250><br>
 
Landscape Mode: 
<img src="https://github.com/kallytang/Flixster-App/blob/master/flixter_app_landscape.gif" width=450><br>

### Notes
All movies are greater than 5 stars, I made it so that movies with 7 stars and up are shown as a backdrop and ones below a rating of 7 are shown as portrait images. Some extraneous files are there because I was going to make a pop up window to show more movie information but found out it was going to be the next assignment. Not sure changing the background to black and font gray counts as improved userface. 

### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
