<!-- Improved compatibility of back to top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Don't forget to give the project a star!
*** Thanks again! Now go create something AMAZING! :D
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
<!-- [![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![LinkedIn][linkedin-shield]][linkedin-url] -->
[![GPLv3][license-shield]][license-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <!-- <a href="https://github.com/github_username/repo_name">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a> -->

<h3 align="center">L2J Stats Service</h3>
  <!-- <p align="center">
    project_description
    <br />
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/github_username/repo_name">View Demo</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Report Bug</a>
    ·
    <a href="https://github.com/github_username/repo_name/issues">Request Feature</a>
  </p> -->
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<!-- [![Product Name Screen Shot][product-screenshot]](https://example.com) -->

<p>A simple service that exposes json strings from l2jgame database to ip:port/info</p>
<p>For example, one could get from localhost:8080/online a json that contains how many players are online/offline</p>
<p>This is particularly useful as it is an intermidiate layer bettween the website and the server's database providing an additional layer of security. It's also faster as it is caching things in a ConcurrentHashMap

<!-- Here's a blank template to get started: To avoid retyping too much info. Do a search and replace with your text editor for the following: `github_username`, `repo_name`, `twitter_handle`, `linkedin_username`, `email_client`, `email`, `project_title`, `project_description` -->

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With
* [![Java]][Java-url]
* [![Gradle]][Gradle-url]
* [![Spring]][Spring-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites
- JDK 11+, reccomended 17
- MariaDB server running with an l2jgame database. (that follows their structure)

### Installation

Install from source code:
1. Clone the repo
   ```
   sh git clone git@github.com:Kam1goroshi/L2JStatsService.git
   ```
2. Navigate to the root project directory
   ```
   cd L2JStatsService
   ```
3. copy src\main\resources\properties\local.properties to src\main\resources\properties\database.properties
   Linux:
   ```
   cp src/main/resources/properties/local.properties src/main/resources/properties/database.properties
   ```
   Windows:
   ```
   copy src\main\resources\properties\local.properties src\main\resources\properties\database.properties
   ```
4. Edit database.properties to match the running server's properties
   (optional) If you need to change the default port which is 8080 edit src/main/resources/application.properties
5. Run the appropriate gradle wrapper to generate the project's jar file. (you might need to change the file's permission to make it executable)
   Linux:
   ```
   ./gradle build
   ```
   Windows:
   ```
   ./gradlew build
   ```
6. Run the service
   Windows:
   ```
    java -jar build\libs\L2JStatsService.jar
   ```
   Linux:
   ```
    java -jar build/libs/L2JStatsService.jar
   ```
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

To be done - Documentation and instructions will be added soon. Ignore this section for now.
Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ROADMAP -->
<!-- ## Roadmap

- [ ] Feature 1
- [ ] Feature 2
- [ ] Feature 3
    - [ ] Nested Feature

See the [open issues](https://github.com/github_username/repo_name/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p> -->



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the GNU General Public License v3. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Discord: Kam1goroshi#5318

Project Link: [https://github.com/Kam1goroshi/L2JStatsService](https://github.com/Kam1goroshi/L2JStatsService)

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

* This README template was adapted from the [template by John Doe](https://github.com/johndoe/readme-template), which is licensed under the MIT license.
* This project exists because of [L2j-server](https://www.l2jserver.com/)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

[license-shield]: https://img.shields.io/github/license/Kam1goroshi/L2JStatsService?style=plastic
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[Java]: https://img.shields.io/badge/Java-darkred?style=for-the-badge&logo=openjdk
[Java-url]: https://www.java.com/
[Gradle]: https://img.shields.io/badge/Gradle-darkgreen?style=for-the-badge&logo=gradle
[Gradle-url]: https://gradle.org/
[Kotlin]: https://img.shields.io/badge/Kotlin-grey?style=for-the-badge&logo=kotlin
[Kotlin-url]: https://kotlinlang.org/
[Spring]: https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[Spring-url]: https://spring.io/