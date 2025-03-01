from sys import argv
import requests
import pandas as pd


def indice_components(indice="sp500",headers={'User-agent': 'Mozilla/5.0'}):
    site = f"https://www.slickcharts.com/{indice}"
    table = pd.read_html(requests.get(site, headers=headers).text)[0]

    #print(table)

    #components = table[['Company', 'Symbol', 'Weight']]
    components = table[['Company', 'Symbol']]
    components = components.assign(Weight=1.0)

    return components.to_csv(index=False)


if __name__ == '__main__':
    my_components = indice_components(indice=argv[1])
    print(my_components)

