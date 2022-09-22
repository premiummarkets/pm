from sys import argv
import requests
import pandas as pd
    
    
def indice_components(indice=None, headers={'User-agent': 'Mozilla/5.0'}):
    '''Downloads list of currently traded tickers on the NIFTY 50, India'''

    site = f"https://finance.yahoo.com/quote/{indice}/components?p={indice}"
    table = pd.read_html(requests.get(site, headers=headers).text)[0]

    components = table[['Symbol', 'Company Name']]

    return components.to_csv(index=False)

if __name__ == '__main__':
    my_components = indice_components(indice=argv[1])
    print(my_components)