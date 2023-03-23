
# Tv Series App (Level 2)

It is an enhanced version of [Tv Series App (Level 1)](https://github.com/BerkErdgn/TvSeriesApp).

This app shows Tv series. It gives information about the selected series, its episodes, its actors and the crew. In addition, detailed information is given about the actors and the people in the crew. There is also a search section in the application. In this way, the desired TV series can be found by searching. And at the top of the application, you can see TvSeries, the new episode of which was released that day.




## Technologies Used

- MVVM
- Retrofit
- Glide
- Rxjava
- Navigation
- LottieFiles

  ## API Usage

```http
    https://api.tvmaze.com/schedule/web?date=2023-01-30
    https://api.tvmaze.com/shows
    https://api.tvmaze.com/search/shows?q=girls
    https://api.tvmaze.com/shows/1?embed=cast
    https://api.tvmaze.com/seasons/1/episodes
    https://api.tvmaze.com/shows/1/crew
    https://api.tvmaze.com/people/1
    https://api.tvmaze.com/episodes/51

```
It allows us to get the details of the desired tv shows.

| Parameter | Type     | Explanation                |
| :-------- | :------- | :------------------------- |
| `web` | `string` | It allows us to get the Today Tv Shows.. | 
| `shows` | `string` | It allows us to receive all Tv serials |
| `shows` | `string` |It allows us to get sought-after tv series. |
| `shows/{idTvSeries}?embed=cast` | `string` | It allows us to get details about a tv series. |
| `{idTvSeries}/episodes` | `string` | it allows us to get episodes of a Tv series |
| `{idTvSeries}/crew` | `string` | it allows us to get Crew of a Tv series |
| `people/{idPeople}` | `string` | it allows us to get actors of a Tv series |
| `episodes/{idEpisodes}` | `string` | It allows us to retrieve an episode detail. |


## Screenshots

Splash Screen | Splash Screen |Login Screen|SignUp Screen|Main Screen|
 --- | --- |  --- | --- | --- | 
![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-1.png?raw=true)| ![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-2.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-3.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-4.png?raw=true)|![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-5.png?raw=true)


Main Screen | TV Series Detail Screen |TV Series Detail Screen|TV Series Detail Screen|Season Screen|
 --- | --- |  --- | --- | --- | 
![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-6.png?raw=true)| ![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-7.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-8.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-9.png?raw=true)|![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-10.png?raw=true)

Actor Screen | Crew Screen |Search Screen|User Screen
 --- | --- |  --- | --- |
![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-11.png?raw=true)| ![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-12.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-13.png?raw=true) |![](https://github.com/BerkErdgn/TvSeriesApp-Level-2/blob/main/sc/TvSeriesApp2-14.png?raw=true)







## Download  

To download the project

```bash 
  1-Press the green "Code" button at the top right of this page.
  2-Click on Download ZIP
  3-Extract the ZIP and open it to Android Studio
```
As an alternative

You can directly download the ZIP by clicking the [link here](https://github.com/BerkErdgn/TvSeriesApp-Level-2/archive/refs/heads/main.zip).


## Find a bug?

If you found an issue or would like to submit an improvement to this project, please submit an issue using the issues tab above.
Thank you very much.
