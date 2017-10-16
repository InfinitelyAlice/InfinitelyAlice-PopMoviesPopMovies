# Project-PopMovies
To use the Movie Database Support, you need to set your own API key.

How-to
Step1) Go to the FetchLoadingTask class in MainActivityFragment
Step2) You'll find this variable setting in the code
private String API_KEY = "";
Step3) Please fill-out your own API Key
----出自kitomiyu

自己独立做还是难以下手，为了继续学习，先用别人的代码。


MainActivityFragment中

 if (moviesInfo == null) {
            moviesInfo = new ArrayList<>();
            imagesAdapter = new MovieImagesAdapter(getActivity(), moviesInfo);
            //Calling the AsyncTask class to start to execute.
            new FetchLoadingTask().execute(getString(R.string.sort_order_upcoming));
        }
        这段内容不知道是什么意图

