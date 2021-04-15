import numpy as np
from matplotlib import pyplot as plt

N=8
samples_vector = np.linspace(0, 2 * np.pi, N)
x1 = np.sin(5 * samples_vector)
x2 = np.cos(2 * samples_vector)

def cconv(x1, x2):
    x2Vector=list(x2[0:1])+(list(x2[1:N]))[::-1]
    x1Matrix = np.zeros((N, N))
    for i in range(int(N)):
        x1Matrix[i]=list(x1[i:N])+list(x1[0:i])
    return np.dot(x2Vector, x1Matrix)

def fftConv(x1, x2):
    x2fft=np.fft.fft(x2)
    x1fft=np.fft.fft(x1)
    mult=x2fft*x1fft
    return np.fft.ifft(mult)

def ccor(x1,x2):
    x1Matrix = np.zeros((N, N))
    for i in range(int(N)):
        x1Matrix[i]=list(x1[N-i:N])+list(x1[0:N-i])
    return np.dot(x1Matrix, x2)


def fftCor(x1, x2):
    x2fft=np.fft.fft(x2)
    x1fft=np.fft.fft(x1)
    x1fft = list(map(lambda x: x.conj(), x1fft))
    mult=x2fft*x1fft
    return np.fft.ifft(mult)


fig, ax = plt.subplots(4, 1)
ax[0].plot(samples_vector, cconv(x1,x2))
ax[0].set_title("Свертка")
ax[1].plot(samples_vector, fftConv(x1,x2))
ax[1].set_title("Свертка БПФ")
ax[2].plot(samples_vector, ccor(x1,x2))
ax[2].set_title("Корреляция")
ax[3].plot(samples_vector, fftCor(x1,x2))
ax[3].set_title("Корреляция БПФ")