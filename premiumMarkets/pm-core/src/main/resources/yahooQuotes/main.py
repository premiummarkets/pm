from sys import argv

import yfinance as yf


def download_quotes(symbol, start, end):
    ticker = yf.Ticker(symbol)

    # get stock info
    # print(ticker.info)

    # get historical market data
    hist = ticker.history(start=start, end=end)

    return hist.to_csv()

if __name__ == '__main__':
    hist = download_quotes(argv[1], argv[2], argv[3])
    print(hist)

