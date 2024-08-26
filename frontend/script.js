const container = document.querySelector('.container');
const search = document.querySelector('.search-box button');
const weatherBox = document.querySelector('.weather-box');
const weatherDetails = document.querySelector('.weather-details');
const error404 = document.querySelector('.not-found');

search.addEventListener('click', () => {
    const city = document.querySelector('#cityInput').value;

    if (city === '') return;

    fetch(`http://localhost:8080/weather?city=${city}`)
        .then(response => response.json())
        .then(json => {
            if (json.condition === 'Unknown') { // Assuming 'Unknown' means an error or invalid city
                container.style.height = '400px';
                weatherBox.style.display = 'none';
                weatherDetails.style.display = 'none';
                error404.style.display = 'block';
                error404.classList.add('fadeIn');
                return;
            }

            error404.style.display = 'none';
            error404.classList.remove('fadeIn');

            const image = document.querySelector('.weather-box img');
            const temperature = document.querySelector('.weather-box .temperature');
            const description = document.querySelector('.weather-box .description');
            const humidity = document.querySelector('.weather-details .humidity span');
            const wind = document.querySelector('.weather-details .wind span');

            // Use json.condition here, as that's the field you're using for weather condition
            switch (json.condition) {
                case 'Clear':
                    image.src = 'src/img/clear.png';
                    break;

                case 'Rain':
                    image.src = 'src/img/rain.png';
                    break;

                case 'Snow':
                    image.src = 'src/img/snow.png';
                    break;

                case 'Clouds':
                    image.src = 'src/img/cloud.png';
                    break;

                case 'Haze':
                    image.src = 'src/img/mist.png';
                    break;

                default:
                    image.src = '';
            }

            temperature.innerHTML = `${json.temp}<span>°C</span>`;
            description.innerHTML = `${json.condition}`;
            humidity.innerHTML = `${json.humidity}%`;
            wind.innerHTML = `${json.wind_speed} Km/h`;

            weatherBox.style.display = '';
            weatherDetails.style.display = '';
            weatherBox.classList.add('fadeIn');
            weatherDetails.classList.add('fadeIn');
            container.style.height = '590px';
        })
        .catch(error => {
            console.error('Error fetching weather data:', error);
            // Handle errors if necessary
        });
});
