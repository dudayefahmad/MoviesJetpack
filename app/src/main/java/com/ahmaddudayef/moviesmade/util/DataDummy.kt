package com.ahmaddudayef.moviesmade.util

import com.ahmaddudayef.moviesmade.data.local.entity.MovieEntity
import com.ahmaddudayef.moviesmade.data.local.entity.TvShowEntity

object DataDummy {

    fun generateDataMoviesDummy(): List<MovieEntity> {
        val listMovie = ArrayList<MovieEntity>()

        listMovie.add(
            MovieEntity(
                id = 634649,
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                title = "Spider-Man: No Way Home",
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                releaseDate = "2021-12-15",
                voteAverage = 8.4,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 557,
                overview = "After being bitten by a genetically altered spider at Oscorp, nerdy but endearing high school student Peter Parker is endowed with amazing powers to become the superhero known as Spider-Man.",
                title = "Spider-Man",
                posterPath = "/gh4cZbhZxyTbgxQPxD0dOudNPTn.jpg",
                backdropPath = "/zp33lkXqcZWyr7iMxzt3lNDtcPv.jpg",
                releaseDate = "2002-05-01",
                voteAverage = 7.2,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 315635,
                overview = "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.",
                title = "Spider-Man: Homecoming",
                posterPath = "/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                backdropPath = "/tTlAA0REGPXSZPBfWyTW9ipIv1I.jpg",
                releaseDate = "2017-07-05",
                voteAverage = 7.4,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 559,
                overview = "The seemingly invincible Spider-Man goes up against an all-new crop of villains—including the shape-shifting Sandman. While Spider-Man’s superpowers are altered by an alien organism, his alter ego, Peter Parker, deals with nemesis Eddie Brock and also gets caught up in a love triangle.",
                title = "Spider-Man 3",
                posterPath = "/qFmwhVUoUSXjkKRmca5yGDEXBIj.jpg",
                backdropPath = "/6MQmtWk4cFwSDyNvIgoJRBIHUT3.jpg",
                releaseDate = "2007-05-01",
                voteAverage = 6.3,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 1930,
                overview = "Peter Parker is an outcast high schooler abandoned by his parents as a boy, leaving him to be raised by his Uncle Ben and Aunt May. Like most teenagers, Peter is trying to figure out who he is and how he got to be the person he is today. As Peter discovers a mysterious briefcase that belonged to his father, he begins a quest to understand his parents' disappearance – leading him directly to Oscorp and the lab of Dr. Curt Connors, his father's former partner. As Spider-Man is set on a collision course with Connors' alter ego, The Lizard, Peter will make life-altering choices to use his powers and shape his destiny to become a hero.",
                title = "The Amazing Spider-Man",
                posterPath = "/fSbqPbqXa7ePo8bcnZYN9AHv6zA.jpg",
                backdropPath = "/sLWUtbrpiLp23a0XDSiUiltdFPJ.jpg",
                releaseDate = "2012-06-23",
                voteAverage = 6.7,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 225914,
                overview = "When an extortionist threatens to force a multi-suicide unless a huge ransom is paid, only Peter Parker can stop him with his new powers as Spider-Man.",
                title = "Spider-Man",
                posterPath = "/nyXfGIkJQgKhugxMVql15URobtt.jpg",
                backdropPath = "/zlpZzccypkAYFZIyYLQcchl90ZC.jpg",
                releaseDate = "1977-09-14",
                voteAverage = 5.5,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 102382,
                overview = "For Peter Parker, life is busy. Between taking out the bad guys as Spider-Man and spending time with the person he loves, Gwen Stacy, high school graduation cannot come quickly enough. Peter has not forgotten about the promise he made to Gwen’s father to protect her by staying away, but that is a promise he cannot keep. Things will change for Peter when a new villain, Electro, emerges, an old friend, Harry Osborn, returns, and Peter uncovers new clues about his past.",
                title = "The Amazing Spider-Man 2",
                posterPath = "/c3e9e18SSlvFd1cQaGmUj5tqL5P.jpg",
                backdropPath = "/mPyiNWS0upEG1mGKOKyCQSoZpnp.jpg",
                releaseDate = "2014-04-16",
                voteAverage = 6.5,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 429617,
                overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                title = "Spider-Man: Far From Home",
                posterPath = "/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg",
                backdropPath = "/vamhMTvh9m9zFHDoR0v1nRtf6T4.jpg",
                releaseDate = "2019-06-28",
                voteAverage = 7.5,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 558,
                overview = "Peter Parker is going through a major identity crisis. Burned out from being Spider-Man, he decides to shelve his superhero alter ego, which leaves the city suffering in the wake of carnage left by the evil Doc Ock. In the meantime, Parker still can't act on his feelings for Mary Jane Watson, a girl he's loved since childhood. A certain anger begins to brew in his best friend Harry Osborn as well...",
                title = "Spider-Man 2",
                posterPath = "/olxpyq9kJAZ2NU1siLshhhXEPR7.jpg",
                backdropPath = "/6al048Lat3eLVQOuKtc9h6Tu94d.jpg",
                releaseDate = "2004-06-25",
                voteAverage = 7.2,
                isFavorite = false
            )
        )

        listMovie.add(
            MovieEntity(
                id = 324857,
                overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                title = "Spider-Man: Into the Spider-Verse",
                posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                backdropPath = "/x2IqsMlpbOhS8zIUJfyl1yO4gHF.jpg",
                releaseDate = "2018-12-06",
                voteAverage = 8.4,
                isFavorite = false
            )
        )

        return listMovie
    }

    fun generateDataTvShowDummy(): List<TvShowEntity> {
        val listTvShow = ArrayList<TvShowEntity>()

        listTvShow.add(
            TvShowEntity(
                id = 634649,
                overview = "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                name = "Spider-Man: No Way Home",
                posterPath = "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                backdropPath = "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                firstAirDate = "2021-12-15",
                voteAverage = 8.4,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 557,
                overview = "After being bitten by a genetically altered spider at Oscorp, nerdy but endearing high school student Peter Parker is endowed with amazing powers to become the superhero known as Spider-Man.",
                name = "Spider-Man",
                posterPath = "/gh4cZbhZxyTbgxQPxD0dOudNPTn.jpg",
                backdropPath = "/zp33lkXqcZWyr7iMxzt3lNDtcPv.jpg",
                firstAirDate = "2002-05-01",
                voteAverage = 7.2,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 315635,
                overview = "Following the events of Captain America: Civil War, Peter Parker, with the help of his mentor Tony Stark, tries to balance his life as an ordinary high school student in Queens, New York City, with fighting crime as his superhero alter ego Spider-Man as a new threat, the Vulture, emerges.",
                name = "Spider-Man: Homecoming",
                posterPath = "/c24sv2weTHPsmDa7jEMN0m2P3RT.jpg",
                backdropPath = "/tTlAA0REGPXSZPBfWyTW9ipIv1I.jpg",
                firstAirDate = "2017-07-05",
                voteAverage = 7.4,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 559,
                overview = "The seemingly invincible Spider-Man goes up against an all-new crop of villains—including the shape-shifting Sandman. While Spider-Man’s superpowers are altered by an alien organism, his alter ego, Peter Parker, deals with nemesis Eddie Brock and also gets caught up in a love triangle.",
                name = "Spider-Man 3",
                posterPath = "/qFmwhVUoUSXjkKRmca5yGDEXBIj.jpg",
                backdropPath = "/6MQmtWk4cFwSDyNvIgoJRBIHUT3.jpg",
                firstAirDate = "2007-05-01",
                voteAverage = 6.3,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 1930,
                overview = "Peter Parker is an outcast high schooler abandoned by his parents as a boy, leaving him to be raised by his Uncle Ben and Aunt May. Like most teenagers, Peter is trying to figure out who he is and how he got to be the person he is today. As Peter discovers a mysterious briefcase that belonged to his father, he begins a quest to understand his parents' disappearance – leading him directly to Oscorp and the lab of Dr. Curt Connors, his father's former partner. As Spider-Man is set on a collision course with Connors' alter ego, The Lizard, Peter will make life-altering choices to use his powers and shape his destiny to become a hero.",
                name = "The Amazing Spider-Man",
                posterPath = "/fSbqPbqXa7ePo8bcnZYN9AHv6zA.jpg",
                backdropPath = "/sLWUtbrpiLp23a0XDSiUiltdFPJ.jpg",
                firstAirDate = "2012-06-23",
                voteAverage = 6.7,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 225914,
                overview = "When an extortionist threatens to force a multi-suicide unless a huge ransom is paid, only Peter Parker can stop him with his new powers as Spider-Man.",
                name = "Spider-Man",
                posterPath = "/nyXfGIkJQgKhugxMVql15URobtt.jpg",
                backdropPath = "/zlpZzccypkAYFZIyYLQcchl90ZC.jpg",
                firstAirDate = "1977-09-14",
                voteAverage = 5.5,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 102382,
                overview = "For Peter Parker, life is busy. Between taking out the bad guys as Spider-Man and spending time with the person he loves, Gwen Stacy, high school graduation cannot come quickly enough. Peter has not forgotten about the promise he made to Gwen’s father to protect her by staying away, but that is a promise he cannot keep. Things will change for Peter when a new villain, Electro, emerges, an old friend, Harry Osborn, returns, and Peter uncovers new clues about his past.",
                name = "The Amazing Spider-Man 2",
                posterPath = "/c3e9e18SSlvFd1cQaGmUj5tqL5P.jpg",
                backdropPath = "/mPyiNWS0upEG1mGKOKyCQSoZpnp.jpg",
                firstAirDate = "2014-04-16",
                voteAverage = 6.5,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 429617,
                overview = "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                name = "Spider-Man: Far From Home",
                posterPath = "/4q2NNj4S5dG2RLF9CpXsej7yXl.jpg",
                backdropPath = "/vamhMTvh9m9zFHDoR0v1nRtf6T4.jpg",
                firstAirDate = "2019-06-28",
                voteAverage = 7.5,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 558,
                overview = "Peter Parker is going through a major identity crisis. Burned out from being Spider-Man, he decides to shelve his superhero alter ego, which leaves the city suffering in the wake of carnage left by the evil Doc Ock. In the meantime, Parker still can't act on his feelings for Mary Jane Watson, a girl he's loved since childhood. A certain anger begins to brew in his best friend Harry Osborn as well...",
                name = "Spider-Man 2",
                posterPath = "/olxpyq9kJAZ2NU1siLshhhXEPR7.jpg",
                backdropPath = "/6al048Lat3eLVQOuKtc9h6Tu94d.jpg",
                firstAirDate = "2004-06-25",
                voteAverage = 7.2,
                isFavorite = false
            )
        )

        listTvShow.add(
            TvShowEntity(
                id = 324857,
                overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                name = "Spider-Man: Into the Spider-Verse",
                posterPath = "/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg",
                backdropPath = "/x2IqsMlpbOhS8zIUJfyl1yO4gHF.jpg",
                firstAirDate = "2018-12-06",
                voteAverage = 8.4,
                isFavorite = false
            )
        )

        return listTvShow
    }

}