import os
import logging
import requests
import urllib.request
import time

logger = logging.getLogger('Pokemons parser')
logger.setLevel(logging.DEBUG)

ch = logging.StreamHandler()
ch.setLevel(logging.DEBUG)

formatter = logging.Formatter(
    '%(asctime)s :: %(name)s :: %(levelname)s :: %(message)s'
)

ch.setFormatter(formatter)
logger.addHandler(ch)

pokemons_list_url = 'https://pokeapi.co/api/v2/pokemon/'

def create_images_directory(dir_name):
    if not os.path.exists(dir_name):
        os.makedirs(dir_name)
        print("Directory " , dir_name ,  " Created ")
    else:
        print("Directory " , dir_name ,  " already exists")

def get_pokemons_list():
    pokemons_list = requests.get(pokemons_list_url).json()['results']

    return pokemons_list

def download_pokemon_image(pokemon_url):
    pokemon_attributes_line = ''
    pokemon_data = requests.get(pokemon_url).json()

    pokemon_name = pokemon_data['name']

    file_name = pokemon_name + '.png'
    image_url = pokemon_data['sprites']['front_default']

    logger.info('Downloading image of {}'.format(pokemon_name))

    urllib.request.urlretrieve(image_url,'images/' + file_name)


if __name__ == '__main__':
    logger.info('===== Get Pokemon Images =====')
    pokemons_list = get_pokemons_list()
    create_images_directory('images')

    downloaded_images = 0
    last_pokemon = 721

    for i in range(0,last_pokemon+1):
        pokemon_data = pokemons_list[i]

        download_pokemon_image(pokemon_data['url'])

