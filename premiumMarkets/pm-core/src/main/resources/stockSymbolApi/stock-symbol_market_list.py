import json

from stocksymbol import StockSymbol

api_key = '12f76181-0a89-4570-8059-fee305082d58'
ss = StockSymbol(api_key)

market_list = ss.market_list
market_json = json.dumps(market_list, indent=None)

print(market_json)

