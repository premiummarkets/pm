from sys import argv

import yfinance as yf


def download_quotes(symbol, interval='NotDefined', period='NotDefined', start=None, end=None):
	ticker = yf.Ticker(symbol)
	
	# fail()

	# get stock info
	# print(ticker.info)

	# get historical market data
	# hist = ticker.history(start=start, end=end)

	if period != 'NotDefined':
		hist = ticker.history(period=period)
	else:
		if interval != 'NotDefined':
			hist = ticker.history(interval=interval,start=start, end=end)
		else:
			hist = ticker.history(start=start, end=end)


	return hist.to_csv()

if __name__ == '__main__':
	hist = download_quotes(argv[1], interval=argv[2], period=argv[3], start=argv[4], end=argv[5])
	print(hist)

