package com.example.thesportnewsandinformation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.thesportnewsandinformation.adapter.ViewPagerAdapter
import com.example.thesportnewsandinformation.data.Athletes
import com.example.thesportnewsandinformation.data.Events
import com.example.thesportnewsandinformation.data.HistoricalArchive
import com.example.thesportnewsandinformation.data.News
import com.example.thesportnewsandinformation.data.Sports
import com.example.thesportnewsandinformation.databinding.ActivityMainBinding
import com.example.thesportnewsandinformation.fragments.AboutMeFragment
import com.example.thesportnewsandinformation.fragments.AthletesFragment
import com.example.thesportnewsandinformation.fragments.EventsFragment
import com.example.thesportnewsandinformation.fragments.HistoricalSportsArchiveFragment
import com.example.thesportnewsandinformation.fragments.NewsFragment
import com.example.thesportnewsandinformation.fragments.SportsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var globalData: GlobalData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitialData()

        val tabLayout: TabLayout = binding.tabLayout
        val viewPager: ViewPager2 = binding.viewPager
        val fragments: List<Fragment> = listOf(
            SportsFragment(),
            NewsFragment(),
            AthletesFragment(),
            EventsFragment(),
            HistoricalSportsArchiveFragment(),
            AboutMeFragment()
        )

        val viewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Sports"
                1 -> "News"
                2 -> "Athletes"
                3 -> "Events"
                4 -> "Historical Sports Archive"
                5 -> "About me"
                else -> "Tab ${position + 1}"
            }
        }.attach()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            binding.viewPager.currentItem = when (item.itemId) {
                R.id.bot_nav_news -> 1
                R.id.bot_nav_event -> 3
                R.id.bot_nav_historical_archive -> 4
                else -> 0
            }
            true
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val itemId = when (position) {
                    1 -> R.id.bot_nav_news
                    3 -> R.id.bot_nav_event
                    4 -> R.id.bot_nav_historical_archive
                    else -> 0
                }
                binding.bottomNavigation.selectedItemId = itemId
            }
        })


    }

    private fun setInitialData() {
        globalData = GlobalData(this)
        globalData.clearAllData()

        val sports = arrayListOf(
            Sports(
                "Archery",
                "In this ancient sport, participants aim to hit a target with arrows from a certain distance. Archery demands precise control, focus, and technique. Athletes draw back their bows, align their sights, and release with precision, aiming for the bullseye. It's not just about strength; it's about mastering the art of subtle movements and breathing techniques to achieve accuracy.",
                "Precision"
            ), Sports(
                "Swimming",
                "Dive into the pool and glide through the water with grace and speed. In swimming, athletes strive to cover a certain distance in the shortest time possible. It's not just about raw speed; it's about perfecting your strokes, mastering your turns, and optimizing your breathing technique. From freestyle sprints to long-distance marathons, swimming tests both physical endurance and efficient technique.",
                "Measure"
            ), Sports(
                "Gymnastics",
                "Enter the world of awe-inspiring athleticism and artistic expression. Gymnasts perform breathtaking acrobatic feats on various apparatus or on the floor. From gravity-defying flips on the vault to elegant routines on the balance beam, gymnastics combines strength, flexibility, and grace. Every twist, flip, and leap is meticulously choreographed, creating a spectacle that mesmerizes audiences worldwide.",
                "Spectacle"
            ), Sports(
                "Boxing",
                "Step into the ring and test your skills in the sweet science of boxing. Boxers aim to punch their opponents while strategically evading incoming blows to score points or achieve a knockout. It's not just about throwing punches; it's about footwork, head movement, and timing. Boxers must display both offensive aggression and defensive prowess, utilizing a mix of jabs, hooks, and uppercuts to outmaneuver their opponents.",
                "Combat"
            ), Sports(
                "Football (Soccer)",
                "Lace up your boots and hit the pitch in the world's most popular sport. In football, or soccer, players aim to kick the ball into the opponent's goal to score points while preventing the opposing team from doing the same. It's a game of skill, strategy, and teamwork, where players must combine precise passing, agile footwork, and tactical awareness to control the game. From breathtaking goals to nail-biting saves, football captivates fans with its fast-paced action and moments of sheer brilliance.",
                "Play"
            )
        )
        globalData.setSports(sports)

        val athletes = arrayListOf(
            Athletes(
                "Sarah Johnson",
                "Archery",
                "USA",
                "Won gold in the 2016 Olympics.",
                2,
                "Started archery at the age of 10 and holds multiple national records."
            ), Athletes(
                "Kim Woo-jin",
                "Archery",
                "South Korea",
                "Holds the world record for the highest score in a single round.",
                3,
                "Known for his consistent performance and dominance in international competitions."
            ), Athletes(
                "Michael Phelps",
                "Swimming",
                "USA",
                "Most decorated Olympian of all time with 23 gold medals.",
                28,
                "Holds numerous world records and is widely regarded as one of the greatest swimmers in history."
            ), Athletes(
                "Katie Ledecky",
                "Swimming",
                "USA",
                "Holds multiple world records in freestyle events.",
                14,
                "Known for her dominance in long-distance swimming and versatility across different events."
            ), Athletes(
                "Simone Biles",
                "Gymnastics",
                "USA",
                "Most decorated gymnast in World Championship history.",
                30,
                "Known for her gravity-defying skills and innovation in gymnastics routines."
            ), Athletes(
                "Kohei Uchimura",
                "Gymnastics",
                "Japan",
                "Considered one of the greatest male gymnasts of all time.",
                21,
                "Known for his elegance and consistency in competitions."
            ), Athletes(
                "Muhammad Ali",
                "Boxing",
                "USA",
                "Regarded as one of the greatest heavyweight boxers of all time.",
                5,
                "Known for his charisma, athleticism, and social activism."
            ), Athletes(
                "Laila Ali",
                "Boxing",
                "USA",
                "Undefeated in her professional boxing career.",
                6,
                "Daughter of Muhammad Ali, she carved her own legacy in women's boxing."
            ), Athletes(
                "Lionel Messi",
                "Football (Soccer)",
                "Argentina",
                "Multiple-time winner of the FIFA Ballon d'Or award.",
                20,
                "Considered one of the greatest footballers of all time, known for his dribbling skills and goal-scoring prowess."
            ), Athletes(
                "Marta Vieira da Silva",
                "Football (Soccer)",
                "Brazil",
                "Holds the record for the most goals scored in FIFA World Cup tournaments.",
                10,
                "Often referred to as the \"Queen of Soccer,\" she is a dominant force in women's football."
            )
        )
        globalData.setAthletes(athletes)

        val events = arrayListOf(
            Events(
                "Olympic Games",
                "07/23/2024",
                "The Olympic Games is a multi-sport event featuring summer and winter sports competitions."
            ), Events(
                "FIFA World Cup",
                "06/20/2026",
                "The FIFA World Cup is an international football competition contested by the senior men's national teams of the members of FIFA."
            ), Events(
                "Wimbledon Championships",
                "06/28/2024",
                "The Wimbledon Championships is the oldest tennis tournament in the world and is widely regarded as the most prestigious."
            ), Events(
                "Tour de France",
                "07/01/2024",
                "The Tour de France is an annual men's multiple-stage bicycle race primarily held in France."
            ), Events(
                "Super Bowl",
                "02/04/2024",
                "The Super Bowl is the championship game of the National Football League (NFL), played annually between the champions of the NFC and AFC."
            ), Events(
                "Cricket World Cup",
                "02/09/2024",
                "The ICC Cricket World Cup is the international championship of One Day International (ODI) cricket."
            ), Events(
                "Australian Open",
                "01/15/2024",
                "The Australian Open is a major tennis tournament held annually in Melbourne, Australia."
            ), Events(
                "UEFA Champions League Final",
                "05/25/2024",
                "The UEFA Champions League Final is the final match of the UEFA Champions League, the premier club football tournament."
            ), Events(
                "NBA Finals",
                "06/01/2024",
                "The NBA Finals is the championship series of the National Basketball Association (NBA), played annually between the winners of the Eastern and Western Conference Finals."
            ), Events(
                "Boston Marathon",
                "04/15/2024",
                "The Boston Marathon is an annual marathon race held in Boston, Massachusetts, on Patriots' Day, the third Monday of April."
            )
        )
        globalData.setEvents(events)
        val historicalArchives = arrayListOf(
            HistoricalArchive(
                "First Modern Olympic Games",
                "04/06/1896",
                "The first modern Olympic Games were held in Athens, Greece, featuring athletes from 14 nations competing in 43 events."
            ), HistoricalArchive(
                "The Battle of the Sexes",
                "09/20/1973",
                "A tennis match between Billie Jean King and Bobby Riggs, which King won, challenging the notion that men were superior athletes."
            ), HistoricalArchive(
                "Miracle on Ice",
                "02/22/1980",
                "The United States men's ice hockey team defeated the Soviet Union in the Winter Olympics, a stunning upset victory."
            ), HistoricalArchive(
                "Jesse Owens at the 1936 Olympics",
                "08/03/1936",
                "Jesse Owens, an African American track and field athlete, won four gold medals at the Berlin Olympics, undermining Hitler's Aryan supremacy ideology."
            ), HistoricalArchive(
                "Ali vs. Foreman: The Rumble in the Jungle",
                "10/30/1974",
                "Muhammad Ali defeated George Foreman in a heavyweight boxing match held in Zaire, Africa, using his famous 'rope-a-dope' strategy."
            ), HistoricalArchive(
                "The Black Sox Scandal",
                "09/28/1920",
                "Eight members of the Chicago White Sox were accused of intentionally losing the 1919 World Series in exchange for money from gamblers."
            ), HistoricalArchive(
                "The Miracle Mets",
                "10/16/1969",
                "The New York Mets, considered underdogs, won the World Series, defeating the heavily favored Baltimore Orioles."
            ), HistoricalArchive(
                "The Munich Massacre",
                "09/05/1972",
                "During the Munich Olympics, Palestinian terrorists took hostage and eventually killed 11 Israeli athletes, leading to increased security measures at future Olympic Games."
            ), HistoricalArchive(
                "The Thrilla in Manila",
                "10/01/1975",
                "A historic boxing match between Muhammad Ali and Joe Frazier, considered one of the greatest fights in boxing history."
            ), HistoricalArchive(
                "The first modern marathon",
                "04/10/1896",
                "The first modern marathon race was held during the inaugural modern Olympic Games in Athens, Greece, following the legendary route of the ancient Greek soldier Pheidippides."
            )
        )
        globalData.setHistoricalArchives(historicalArchives)
        val news = arrayListOf(
            News(
                "Naomi Osaka Wins Australian Open Women's Singles Title",
                "https://people.com/thmb/nghumA-S06w9IynI8jOE2Tb3BDc=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(734x239:736x241):format(webp)/naomi-osaka-1-f827a95ebf41459ea3bcf8ef6a55f9b5.jpg",
                "Japanese tennis star Naomi Osaka clinched her fourth Grand Slam title by winning the women's singles event at the Australian Open, defeating her opponent in straight sets."
            ), News(
                "Tom Brady Announces Retirement After 22 Seasons in the NFL",
                "https://pbs.twimg.com/media/FKhLS54XIA8gx4z?format=jpg&name=small",
                "Legendary quarterback Tom Brady has officially announced his retirement from professional football after an illustrious career spanning 22 seasons."
            ), News(
                "LeBron James Becomes NBA's All-Time Leading Scorer",
                "https://cdn.nba.com/manage/2023/02/Lebron-James-Mixtape-Gold-v516x9-safezone-1-1568x882.png",
                "Los Angeles Lakers' forward LeBron James has surpassed Kareem Abdul-Jabbar as the NBA's all-time leading scorer, cementing his legacy as one of the greatest basketball players in history."
            ), News(
                "Simone Biles Returns to Gymnastics Competition, Wins Gold",
                "https://media.bleacherreport.com/image/upload/x_37,y_43,w_1732,h_1153,c_crop/w_800,h_533,c_fill/v1691288192/jnhcbmd0en2zrmcaijt1.jpg",
                "After taking a break from competition, gymnastics superstar Simone Biles made a triumphant return, capturing gold in the all-around event at the World Championships."
            ), News(
                "Lewis Hamilton Wins Record 8th Formula 1 World Championship",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKvvxicNsK0OL4IjYhg_mIOruQpGJPhQlKk6oVpHi3V_5RfFgsxLW0cpVIXZccJdzvvIk&usqp=CAU",
                "British racing driver Lewis Hamilton has made history by winning his eighth Formula 1 World Championship title, solidifying his status as one of the sport's greatest drivers."
            ), News(
                "2024 Summer Olympics Opening Ceremony Wows Spectators",
                "https://dims.apnews.com/dims4/default/8aebf7d/2147483647/strip/true/crop/4441x2959+30+0/resize/980x653!/format/webp/quality/90/?url=https%3A%2F%2Fassets.apnews.com%2Fe3%2Fdf%2F72fd9259eabcd8483884b10ebcdc%2F5a38eca1ca8d458f9ba5e1d48e797dbc",
                "The opening ceremony of the 2024 Summer Olympics in Paris captivated audiences worldwide with dazzling performances, cultural displays, and the lighting of the Olympic cauldron."
            )
        )
        globalData.setNews(news)
    }
}