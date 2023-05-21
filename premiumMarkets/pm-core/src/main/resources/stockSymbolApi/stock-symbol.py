import json
from sys import argv

from stocksymbol import StockSymbol

api_key = '12f76181-0a89-4570-8059-fee305082d58'


def get_symbols(market="america", index="NDX"):
    ss = StockSymbol(api_key)
    # symbol_list_us = ss.get_symbol_list(market="US")
    symbol_list = ss.get_symbol_list(market=market, index=index)
    symbol_json = json.dumps(symbol_list, indent=2)
    return symbol_json

if __name__ == '__main__':
    symbols = get_symbols(market=argv[1], index=argv[2])
    print(symbols)
